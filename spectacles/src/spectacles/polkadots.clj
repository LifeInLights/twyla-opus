(ns spectacles.polkadots
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/smooth)
  (q/frame-rate 60)
  {:vdots [{:x 116 :y -20 :diameter 40}
           {:x 252 :y -20 :diameter 40}
           {:x 388 :y -20 :diameter 40}
           {:x 524 :y -20 :diameter 40}
           {:x 660 :y -20 :diameter 40}
           {:x 116 :y 84 :diameter 40}
           {:x 252 :y 84 :diameter 40}
           {:x 388 :y 84 :diameter 40}
           {:x 524 :y 84 :diameter 40}
           {:x 660 :y 84 :diameter 40}
           {:x 116 :y 188 :diameter 40}
           {:x 252 :y 188 :diameter 40}
           {:x 388 :y 188 :diameter 40}
           {:x 524 :y 188 :diameter 40}
           {:x 660 :y 188 :diameter 40}
           {:x 116 :y 292 :diameter 40}
           {:x 252 :y 292 :diameter 40}
           {:x 388 :y 292 :diameter 40}
           {:x 524 :y 292 :diameter 40}
           {:x 660 :y 292 :diameter 40}
           {:x 116 :y 396 :diameter 40}
           {:x 252 :y 396 :diameter 40}
           {:x 388 :y 396 :diameter 40}
           {:x 524 :y 396 :diameter 40}
           {:x 660 :y 396 :diameter 40}]
   :hdots [{:x 116 :y 32 :diameter 40}
           {:x 252 :y 32 :diameter 40}
           {:x 388 :y 32 :diameter 40}
           {:x 524 :y 32 :diameter 40}
           {:x 660 :y 32 :diameter 40}
           {:x 116 :y 136 :diameter 40}
           {:x 252 :y 136 :diameter 40}
           {:x 388 :y 136 :diameter 40}
           {:x 524 :y 136 :diameter 40}
           {:x 660 :y 136 :diameter 40}
           {:x 116 :y 240 :diameter 40}
           {:x 252 :y 240 :diameter 40}
           {:x 388 :y 240 :diameter 40}
           {:x 524 :y 240 :diameter 40}
           {:x 660 :y 240 :diameter 40}
           {:x 116 :y 344 :diameter 40}
           {:x 252 :y 344 :diameter 40}
           {:x 388 :y 344 :diameter 40}
           {:x 524 :y 344 :diameter 40}
           {:x 660 :y 344 :diameter 40}
           {:x 116 :y 448 :diameter 40}
           {:x 252 :y 448 :diameter 40}
           {:x 388 :y 448 :diameter 40}
           {:x 524 :y 448 :diameter 40}
           {:x 660 :y 448 :diameter 40}]})

(defn draw [state]
  (q/background 0)
  (q/no-stroke)

  (q/fill 255 0 0 255)
  (doseq [dot (:vdots state)]
    (q/ellipse (:x dot) (:y dot) (:diameter dot) (:diameter dot)))

  (q/fill 255 255 0 255)
  (doseq [dot (:hdots state)]
    (q/ellipse (:x dot) (:y dot) (:diameter dot) (:diameter dot))))

(defn update-y [operation m]
  (let [y (:y m)
        radius (* 0.5 (:diameter m))]
    (if (< y (- 0 radius))
      (assoc m :y (+ (q/height) radius))
      (if (> y (+ (q/height) radius))
        (assoc m :y (- 0 radius))
        (update-in m [:y] operation 1)))))

(defn update-x [operation m]
  (let [x (:x m)
        radius (* 0.5 (:diameter m))]
    (if (< x (- 0 radius))
      (assoc m :x (+ (q/width) radius))
      (if (> x (+ (q/width) radius))
        (assoc m :x (- 0 radius))
        (update-in m [:x] operation 1)))))

(defn update [state]
  (let [vdots (:vdots state)
        hdots (:hdots state)
        move_left (partial update-x -)
        move_right (partial update-x +)
        move_up (partial update-y -)
        move_down (partial update-y +)]

    (assoc state :vdots (map move_up (map move_left vdots)) :hdots (map move_up (map move_right hdots)))))

;; (q/defsketch practice
;;   :title "Polka Dots"
;;   :size :fullscreen
;;   :setup setup
;;   :draw draw
;;   :update update-state
;;   :features [:keep-on-top]
;;   :middleware [m/fun-mode])

