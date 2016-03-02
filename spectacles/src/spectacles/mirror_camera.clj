(ns spectacles.mirror-camera
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m])
  (:import [processing.video Capture]))

; Inspired by mirror2 from quil examples

(defn setup []

  (def cell-size 15)
  (def cols (/ (q/width) cell-size))
  (def rows (/ (q/height) cell-size))
  (q/color-mode :rgb 255 255 255 100)
  (q/rect-mode :center)

  (println "Initializing Camera...")
  (let [camera (Capture. (quil.applet/current-applet) 1024 768 "/dev/video1" 30)]
    (do
      (.start camera))
    {:camera camera})
  )

(defn update [state]
  state)

(defn draw [state]
  (if (.available (:camera state))
    (do
      (.read (:camera state))
      (.loadPixels (:camera state))
      (q/background 0 0 255)
      (doseq [i (range cols)
              j (range rows)]

        (let [x (* i cell-size)
              y (* j cell-size)
              pixels (.pixels (:camera state))
              location (+ (- 1024 x 1) (* 1024 y))
              color (nth pixels location)
              size (* (/ (q/brightness color) 255) cell-size)
              ]
          (q/fill 255)
          (q/no-stroke)
          (q/rect (+ x (/ cell-size 2)) (+ y (/ cell-size 2)) size size)
          )
        )
      )
    )
  )
