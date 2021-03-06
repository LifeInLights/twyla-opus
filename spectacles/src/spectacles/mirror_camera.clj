(ns spectacles.mirror-camera
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m])
  (:import [processing.video Capture]))

; Inspired by mirror2 from processing-video examples

(defn setup []

  (def camera-specs {:device "/dev/video0"
                     :width 1280
                     :height 720
                     :frame-rate 30})
  (def cell-size 20)
  (def cols (/ (:width camera-specs) cell-size))
  (def rows (/ (:height camera-specs) cell-size))
  (q/color-mode :rgb 255 255 255 100)
  (q/rect-mode :center)

  (println "Initializing Camera...")
  (let [camera (Capture. (quil.applet/current-applet)
                         (:width camera-specs)
                         (:height camera-specs)
                         (:device camera-specs)
                         (:frame-rate camera-specs))]
    (q/fill 255 255 255 90)
    (q/no-stroke)
    (do
      (.start camera))
    {:camera camera})
  )

(defn update [state]
  (if (.available (:camera state))
    (do
      (.read (:camera state))
      (.loadPixels (:camera state))
      (assoc state :pixels (.pixels (:camera state))))
    state))

(defn draw [state]
  (q/background 2 89 15)
  (doseq [i (range cols)
          j (range rows)]
    (let [x (* i cell-size)
          y (* j cell-size)
          pixels (:pixels state)
          location (+ (- (:width camera-specs) x 1) (* (:width camera-specs) y))
          color (nth pixels location)
          size (* (/ (q/brightness color) 255) cell-size)
          ]
      (q/ellipse  (+ x (/ cell-size 2)) (+ y (/ cell-size 2)) size size))))


