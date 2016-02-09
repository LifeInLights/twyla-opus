(ns spectacles.cyberspeed
  (:require [quil.core :as q]))

;; Life In Lights sketch
;; Title: CyberSpeed
;; Author: Maria Jackson

(defn setup [] {})

(defn update [state] state)

(defn draw [state]
  (q/fill 255 0 0)
  (q/line 0 0 (q/random 0 1000)  (q/random 0 1000))
  (q/ellipse 50 50 100 100)
  (q/stroke 255 (q/random 0 255) (q/random 0 255))
  (q/fill 255 255 255 2)
  (q/rect 0 0 (q/width) (q/height)))

;; (q/defsketch CyberSpeed
;;   :title "CyberSpeed"
;;   :size [500 500]
;;   :setup setup
;;   :draw draw
;;   :features [:keep-on-top])
