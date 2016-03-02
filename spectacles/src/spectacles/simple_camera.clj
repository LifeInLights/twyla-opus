(ns spectacles.simple-camera
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m])
  (:import [processing.video Capture]))

;; Use of Video/Capture documented at https://github.com/quil/quil/issues/75

(def camera-specs {:device "/dev/video0"
                   :width 1024
                   :height 768
                   :frame-rate 30})

(defn setup []
  (println "Initializing Camera...")
  (let [camera (Capture. (quil.applet/current-applet)
                         (:width camera-specs)
                         (:height camera-specs)
                         (:device camera-specs)
                         (:frame-rate camera-specs))]
    (do
      (.start camera))
    {:camera camera})
)

(defn update [state]
  (if (.available (:camera state))
      (.read (:camera state)))
  state)

(defn draw [state]
  (q/image (:camera state) 0 0))

;; (q/defsketch getting-started-capture
;;   :host "GettingStartedCapture"
;;   :size [1280 720]
;;   :setup setup
;;   :update update
;;   :draw draw
;;   :middleware [m/fun-mode])

