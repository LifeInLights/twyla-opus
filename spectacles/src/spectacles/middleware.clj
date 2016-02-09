(ns spectacles.middleware
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn show-frame-rate-middleware [options]
  (let [draw (:draw options (fn []))
        updated-draw (fn []
                       (draw)
                       (q/fill 255)
                       (q/no-stroke)
                       (q/rect 1 1 120 20)
                       (q/fill 0)
                       (q/text (str "FPS:" (q/current-frame-rate)) 12 12))]
    (assoc options :draw updated-draw)))

(defn show-frame-count-middleware [options]
  (let [draw (:draw options (fn []))
        updated-draw (fn []
                       (draw)
                       (q/fill 0)
                       (q/text (str "FC: " (q/frame-count)) (- (q/width) 70) 10))]
    (assoc options :draw updated-draw)))
