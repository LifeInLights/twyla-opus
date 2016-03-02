(ns spectacles.simple-camera
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m])
  (:import [processing.video Capture]))

;; Use of Video/Capture documented at https://github.com/quil/quil/issues/75

(defn setup []
  (println "Initializing Camera...")
  (let [;; If using a discovered list of cameras...
        ;;cameras (Capture/list)
        ;;camera (Capture. (quil.applet/current-applet) (first cameras))
        ;; Otherwise, pick it explicitly...
                                        ;        camera (Capture. (quil.applet/current-applet) 1280 720 "/dev/video1" 20)
        camera (Capture. (quil.applet/current-applet) 1024 768 "/dev/video1" 15)
        ]
    (do
;;      (clojure.pprint/pprint cameras) ; Display a list of known cameras
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

