cmake_minimum_required(VERSION 3.3)
project(hand_evaluator)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
    evaluator.c
    hand_eval.c
    hand_eval.h)

add_executable(hand_evaluator ${SOURCE_FILES})