#!/bin/sh

echo Turn on projector
irsend SEND_ONCE /home/pi/lircd.conf KEY_POWER

echo Start presentation
cd /home/pi/twyla-opus/spectacles && export DISPLAY=:0 && /usr/local/bin/lein run -m spectacles.presenter >> /home/pi/presentation.log 2>&1

echo Sleeping
sleep 120

echo Shut off presentation
pkill -f "java.*lein"

echo Shut off projector
irsend SEND_ONCE /home/pi/lircd.conf KEY_POWER && sleep 3 && irsend SEND_ONCE /home/pi/lircd.conf KEY_POWER
