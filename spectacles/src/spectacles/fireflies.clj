(ns spectacles.fireflies
  (:require [quil.core :as q]
            [quil.middleware :as m]))


(defn generate_fireflies
  [count]

  {:fireflies (into [] (repeatedly count (fn [] [(q/random (q/width)) (q/random (q/height))])))
   :velocity (into [] (repeatedly count (fn [] '[1 1])))
   :acceleration (into [] (repeatedly count (fn [] '[1 1])))
   :on (into [] (repeatedly count (fn [] true)))})

(defn setup []
  (q/smooth)
  (q/frame-rate 30)
  (generate_fireflies 40)
  )

(defn draw-firefly
  [[[x y] on]]

  (if on
    (do 
      (q/fill 0 255 0 128)
      (q/ellipse x y 25 25)
      (q/fill 100 255 100 128)
      (q/ellipse x y 15 15)
      (q/fill 200 255 200 128)
      (q/ellipse x y 10 10)
      (q/fill 255 255 255 128)
      (q/ellipse x y 5 5))
    (do
      (q/fill 0 32 0 128)
      (q/ellipse x y 20 20))))

(defn draw [state]
  (q/background 0)
  (q/no-stroke)

  (doseq [firefly (map vector (:fireflies state) (:on state))]
    (draw-firefly firefly)))

(defn update-acceleration
  [[x y] [ax ay]]

  [(* 0.0002 (- (q/mouse-x) x))
   (* 0.0002 (- (q/mouse-y) y))])

(defn limit-velocity
  [a limit]

  (if (< 0 limit)
    (if (< 0 a)
      (min a limit)
      (max a (* -1 limit)))
    (if (< 0 a)
      (min a (* -1 limit))
      (max a limit))))

(defn update-velocity
  [[x y] [vx vy] [ax ay]]

  (let [new-x (+ vx ax)
        new-y (+ vy ay)]
    [(limit-velocity new-x (- (q/mouse-x) x)) (limit-velocity new-y (- (q/mouse-y) y))]))

(defn update-firefly
  [[x y] [vx vy]]
  (if (> (q/random 1000) 998)
    [(q/random (q/width)) (q/random (q/height))]
    [(+ x vx) (+ y vy)])
  )

(defn update-on
  [on]
  (if on
    (< (q/random 1000) 950)
    (< (q/random 100) 5)))

(defn update [state]
  (assoc state :fireflies (into [] (map update-firefly (:fireflies state) (:velocity state)))
         :velocity (into [] (map update-velocity (:fireflies state) (:velocity state) (:acceleration state)))
         :acceleration (into [] (map update-acceleration (:fireflies state) (:acceleration state)))
         :on (into [] (map update-on (:on state)))
         ))

;; (q/defsketch practice
;;   :title "Fireflies"
;;   :size :fullscreen
;;   :setup setup
;;   :draw draw
;;   :update update
;;   :features [:keep-on-top]
;;   :middleware [m/fun-mode])

