(ns spectacles.whalesong
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup [] )

(defn update [state] state)

(defn draw [state]
  (q/frame-rate 2)
  ;; (q/background 127 127 127 64)
  (q/stroke 0 0 0)
  (q/fill 0 0 0 32)
  (q/rect 0 0 (q/width) (q/height))
  (let [x1 (q/random (q/width))
        y1 (q/random (q/height))
        x2 (q/random (q/width))
        y2 (q/random (q/height))]
    (q/no-fill)
    (q/stroke-weight 4)
    (q/stroke 235 235 235)
    (q/bezier 50 50 x1 y1 x2 y2 (- (q/width) 50) (- (q/height) 50))
    (q/stroke-weight 6)
    (q/point x1 y1)
    (q/point x1 y2)))

;; (q/defsketch whalesong
;;   :title "Whalesong"
;;   :size [800 600]
;;   :setup setup
;;   :draw draw
;;   :features [:always-on-top]
;;   :middleware [show-frame-rate-middleware show-frame-count-middleware])
