#!/usr/bin/env python3
#----------------------------------------------------------------------------
# Copyright (c) 2018 FIRST. All Rights Reserved.
# Open Source Software - may be modified and shared by FRC teams. The code
# must be accompanied by the FIRST BSD license file in the root directory of
# the project.
#----------------------------------------------------------------------------

import cv2
import numpy
import math
from enum import Enum

class GripPipeline:
    """
    An OpenCV pipeline generated by GRIP.
    """
    
    def __init__(self):
        """initializes all values to presets or None if need to be set
        """

        self.__resize_image_width = 160.0
        self.__resize_image_height = 120.0
        self.__resize_image_interpolation = cv2.INTER_CUBIC

        self.resize_image_output = None

        self.__blur_input = self.resize_image_output
        self.__blur_type = BlurType.Box_Blur
        self.__blur_radius = 2.003003146197345

        self.blur_output = None

        self.__hsl_threshold_input = self.blur_output
        self.__hsl_threshold_hue = [15.107914698209694, 45.87030912015219]
        self.__hsl_threshold_saturation = [39.74820289680426, 217.28668388653128]
        self.__hsl_threshold_luminance = [0.0, 255.0]

        self.hsl_threshold_output = None

        self.__find_contours_input = self.hsl_threshold_output
        self.__find_contours_external_only = True

        self.find_contours_output = None

        self.__filter_contours_contours = self.find_contours_output
        self.__filter_contours_min_area = 500.0
        self.__filter_contours_min_perimeter = 0.0
        self.__filter_contours_min_width = 0
        self.__filter_contours_max_width = 1000
        self.__filter_contours_min_height = 0
        self.__filter_contours_max_height = 1000
        self.__filter_contours_solidity = [0, 100]
        self.__filter_contours_max_vertices = 1000000
        self.__filter_contours_min_vertices = 0
        self.__filter_contours_min_ratio = 0
        self.__filter_contours_max_ratio = 1000

        self.filter_contours_output = None


    def process(self, source0):
        """
        Runs the pipeline and sets all outputs to new values.
        """
        # Step Resize_Image0:
        self.__resize_image_input = source0
        (self.resize_image_output) = self.__resize_image(self.__resize_image_input, self.__resize_image_width, self.__resize_image_height, self.__resize_image_interpolation)

        # Step Blur0:
        self.__blur_input = self.resize_image_output
        (self.blur_output) = self.__blur(self.__blur_input, self.__blur_type, self.__blur_radius)

        # Step HSL_Threshold0:
        self.__hsl_threshold_input = self.blur_output
        (self.hsl_threshold_output) = self.__hsl_threshold(self.__hsl_threshold_input, self.__hsl_threshold_hue, self.__hsl_threshold_saturation, self.__hsl_threshold_luminance)

        # Step Find_Contours0:
        self.__find_contours_input = self.hsl_threshold_output
        (self.find_contours_output) = self.__find_contours(self.__find_contours_input, self.__find_contours_external_only)

        # Step Filter_Contours0:
        self.__filter_contours_contours = self.find_contours_output
        (self.filter_contours_output) = self.__filter_contours(self.__filter_contours_contours, self.__filter_contours_min_area, self.__filter_contours_min_perimeter, self.__filter_contours_min_width, self.__filter_contours_max_width, self.__filter_contours_min_height, self.__filter_contours_max_height, self.__filter_contours_solidity, self.__filter_contours_max_vertices, self.__filter_contours_min_vertices, self.__filter_contours_min_ratio, self.__filter_contours_max_ratio)


    @staticmethod
    def __resize_image(input, width, height, interpolation):
        """Scales and image to an exact size.
        Args:
            input: A numpy.ndarray.
            Width: The desired width in pixels.
            Height: The desired height in pixels.
            interpolation: Opencv enum for the type fo interpolation.
        Returns:
            A numpy.ndarray of the new size.
        """
        return cv2.resize(input, ((int)(width), (int)(height)), 0, 0, interpolation)

    @staticmethod
    def __blur(src, type, radius):
        """Softens an image using one of several filters.
        Args:
            src: The source mat (numpy.ndarray).
            type: The blurType to perform represented as an int.
            radius: The radius for the blur as a float.
        Returns:
            A numpy.ndarray that has been blurred.
        """
        if(type is BlurType.Box_Blur):
            ksize = int(2 * round(radius) + 1)
            return cv2.blur(src, (ksize, ksize))
        elif(type is BlurType.Gaussian_Blur):
            ksize = int(6 * round(radius) + 1)
            return cv2.GaussianBlur(src, (ksize, ksize), round(radius))
        elif(type is BlurType.Median_Filter):
            ksize = int(2 * round(radius) + 1)
            return cv2.medianBlur(src, ksize)
        else:
            return cv2.bilateralFilter(src, -1, round(radius), round(radius))

    @staticmethod
    def __hsl_threshold(input, hue, sat, lum):
        """Segment an image based on hue, saturation, and luminance ranges.
        Args:
            input: A BGR numpy.ndarray.
            hue: A list of two numbers the are the min and max hue.
            sat: A list of two numbers the are the min and max saturation.
            lum: A list of two numbers the are the min and max luminance.
        Returns:
            A black and white numpy.ndarray.
        """
        out = cv2.cvtColor(input, cv2.COLOR_BGR2HLS)
        return cv2.inRange(out, (hue[0], lum[0], sat[0]),  (hue[1], lum[1], sat[1]))

    @staticmethod
    def __find_contours(input, external_only):
        """Sets the values of pixels in a binary image to their distance to the nearest black pixel.
        Args:
            input: A numpy.ndarray.
            external_only: A boolean. If true only external contours are found.
        Return:
            A list of numpy.ndarray where each one represents a contour.
        """
        if(external_only):
            mode = cv2.RETR_EXTERNAL
        else:
            mode = cv2.RETR_LIST
        method = cv2.CHAIN_APPROX_SIMPLE
        im2, contours, hierarchy =cv2.findContours(input, mode=mode, method=method)
        return contours

    @staticmethod
    def __filter_contours(input_contours, min_area, min_perimeter, min_width, max_width,
                        min_height, max_height, solidity, max_vertex_count, min_vertex_count,
                        min_ratio, max_ratio):
        """Filters out contours that do not meet certain criteria.
        Args:
            input_contours: Contours as a list of numpy.ndarray.
            min_area: The minimum area of a contour that will be kept.
            min_perimeter: The minimum perimeter of a contour that will be kept.
            min_width: Minimum width of a contour.
            max_width: MaxWidth maximum width.
            min_height: Minimum height.
            max_height: Maximimum height.
            solidity: The minimum and maximum solidity of a contour.
            min_vertex_count: Minimum vertex Count of the contours.
            max_vertex_count: Maximum vertex Count.
            min_ratio: Minimum ratio of width to height.
            max_ratio: Maximum ratio of width to height.
        Returns:
            Contours as a list of numpy.ndarray.
        """
        output = []
        for contour in input_contours:
            x,y,w,h = cv2.boundingRect(contour)
            if (w < min_width or w > max_width):
                continue
            if (h < min_height or h > max_height):
                continue
            area = cv2.contourArea(contour)
            if (area < min_area):
                continue
            if (cv2.arcLength(contour, True) < min_perimeter):
                continue
            hull = cv2.convexHull(contour)
            solid = 100 * area / cv2.contourArea(hull)
            if (solid < solidity[0] or solid > solidity[1]):
                continue
            if (len(contour) < min_vertex_count or len(contour) > max_vertex_count):
                continue
            ratio = (float)(w) / h
            if (ratio < min_ratio or ratio > max_ratio):
                continue
            output.append(contour)
        return output


BlurType = Enum('BlurType', 'Box_Blur Gaussian_Blur Median_Filter Bilateral_Filter')




import json
import time
import sys
import threading

from cscore import CameraServer, VideoSource, UsbCamera, CvSink
from networktables import NetworkTables

#   JSON format:
#   {
#       "team": <team number>,
#       "ntmode": <"client" or "server", "client" if unspecified>
#       "cameras": [
#           {
#               "name": <camera name>
#               "path": <path, e.g. "/dev/video0">
#               "pixel format": <"MJPEG", "YUYV", etc>   // optional
#               "width": <video mode width>              // optional
#               "height": <video mode height>            // optional
#               "fps": <video mode fps>                  // optional
#               "brightness": <percentage brightness>    // optional
#               "white balance": <"auto", "hold", value> // optional
#               "exposure": <"auto", "hold", value>      // optional
#               "properties": [                          // optional
#                   {
#                       "name": <property name>
#                       "value": <property value>
#                   }
#               ]
#           }
#       ]
#   }

configFile = "/boot/frc.json"

class CameraConfig: pass

team = None
server = False
cameraConfigs = []

"""Report parse error."""
def parseError(str):
    print("config error in '" + configFile + "': " + str, file=sys.stderr)

"""Read single camera configuration."""
def readCameraConfig(config):
    cam = CameraConfig()

    # name
    try:
        cam.name = config["name"]
    except KeyError:
        parseError("could not read camera name")
        return False

    # path
    try:
        cam.path = config["path"]
    except KeyError:
        parseError("camera '{}': could not read path".format(cam.name))
        return False

    cam.config = config

    cameraConfigs.append(cam)
    return True

"""Read configuration file."""
def readConfig():
    global team
    global server

    # parse file
    try:
        with open(configFile, "rt") as f:
            j = json.load(f)
    except OSError as err:
        print("could not open '{}': {}".format(configFile, err), file=sys.stderr)
        return False

    # top level must be an object
    if not isinstance(j, dict):
        parseError("must be JSON object")
        return False

    # team number
    try:
        team = j["team"]
    except KeyError:
        parseError("could not read team number")
        return False

    # ntmode (optional)
    if "ntmode" in j:
        str = j["ntmode"]
        if str.lower() == "client":
            server = False
        elif str.lower() == "server":
            server = True
        else:
            parseError("could not understand ntmode value '{}'".format(str))

    # cameras
    try:
        cameras = j["cameras"]
    except KeyError:
        parseError("could not read cameras")
        return False
    for camera in cameras:
        if not readCameraConfig(camera):
            return False

    return True

"""Start running the camera."""
def startCamera(config):
    print("Starting camera '{}' on {}".format(config.name, config.path))
    camera = UsbCamera(name=config.name, path=config.path)
    CameraServer.getInstance().addCamera(camera)
    #camera = CameraServer.getInstance() \
    #    .startAutomaticCapture(name=config.name, path=config.path)

    camera.setConfigJson(json.dumps(config.config))

    return camera

def makeBoundBox(input_image, contours):
    """Returns: (output_image, x of first box, y of first box)"""
    width = 3
    rx = -1
    ry = -1
    if contours is not None:
        for contour in contours:
            x,y,w,h = cv2.boundingRect(contour)
            if rx == -1:
                rx = round(x+w/2)
            if ry == -1:
                ry = round(y+h/2)
            mh,mw,d = input_image.shape
            input_image[max(0,y-width):min(mh,y+width),max(0,x-width):min(mw,x+w+width)] = [255,0,255]
            input_image[max(0,y+h-width):min(mh,y+h+width),max(0,x-width):min(mw,x+w+width)] = [255,0,255]
            input_image[max(0,y):min(mh,y+h),max(0,x-width):min(mw,x+width)] = [255,0,255]
            input_image[max(0,y):min(mh,y+h),max(0,x+w-width):min(mw,x+w+width)] = [255,0,255]
            
    return (input_image, rx, ry)

if __name__ == "__main__":
    time.sleep(60)
    if len(sys.argv) >= 2:
        configFile = sys.argv[1]

    # read configuration
    if not readConfig():
        sys.exit(1)

    # start NetworkTables
    if server:
        print("Setting up NetworkTables server")
        NetworkTables.startServer()
    else:
        print("Setting up NetworkTables client for team {}".format(team))
##        cond = threading.Condition()
##        notified = [False]
##        def connectionListener(connected, info):
##            with cond:
##                notified[0] = True
##                cond.notify()
##
        NetworkTables.startClientTeam(team)
##        NetworkTables.addConnectionListener(connectionListener, immediateNotify=False)
##
##        with cond:
##            print("Waiting")
##            if not notified[0]:
##                cond.wait()
        

    dashboard = NetworkTables.getTable("SmartDashboard")
    # start cameras
    cameras = []
    for cameraConfig in cameraConfigs:
        cameras.append(startCamera(cameraConfig))

    cs = CameraServer.getInstance()
    #serv = cs.addServer(name="pi_cam_test")
    #serv.setSource(cameras[0])
    gp = GripPipeline()
    vid = CvSink("some name")
    vid.setSource(cameras[0])
    vid2 = CvSink("some name 2")
    if len(cameras) < 2:
        vid2.setSource(cameras[0])
    else:
        vid2.setSource(cameras[1])
    out = cs.putVideo("meh", 160, 120)
    #thresh_out = cs.putVideo("thresh", 160, 120)

    # putting default values
    dashboard.putBoolean("disc overlay", False)
    
    # loop forever
    while True:
        data = dashboard.getString("camera", "front")
        s = numpy.zeros((160, 120, 3), dtype = "uint8")
        if data == "front":
            time, s = vid.grabFrame(s)
        else:
            time, s = vid2.grabFrame(s)
            #cv2.flip(s, flipCode=1, dst=s)
        data = dashboard.getBoolean("disc overlay", False)
        if data == True:
            try:
                gp.process(s)
            except Exception as e:
                pass
            (s, x, y) = makeBoundBox(s, gp.filter_contours_output)
            dashboard.putNumber("disk_x", x)
            dashboard.putNumber("disk_y", y)
        out.putFrame(s)
##        s = numpy.zeros((160, 120, 3), dtype = "uint8")
##        time, s = vid2.grabFrame(s)
##        try:
##            gp.process(s)
##        except Exception as e:
##            pass
##        final_img = makeBoundBox(s, gp.filter_contours_output)
##        #final_img = makeBoundBox(gp.hsl_threshold_output, gp.filter_contours_output)
##        thresh_out.putFrame(final_img)
