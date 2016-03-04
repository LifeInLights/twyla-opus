(ns spectacles.fireflies
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;; (defn create-firefly
;;   []
;;   (let [gr (q/create-graphics 26 26 :java2d)]
;;        (q/with-graphics gr
;;          (do
;;            (q/background 255)
;;            (q/fill 0 255 0 128)
;;            (q/ellipse 13 13 26 26)
;;            (q/fill 100 255 100 128)
;;            (q/ellipse 13 13 15 15)
;;            (q/fill 200 255 200 128)
;;            (q/ellipse 13 13 10 10)
;;            (q/fill 255 255 255 128)
;;            (q/ellipse 13 13 5 5)))))

(defn generate-fireflies
  [count]

  {:fireflies (into [] (repeatedly count (fn [] [(q/random (q/width)) (q/random (q/height))])))
   :velocity (into [] (repeatedly count (fn [] '[1 1])))
   :acceleration (into [] (repeatedly count (fn [] '[1 1])))
   :on (into [] (repeatedly count (fn [] true)))
   ;; :firefly_graphic (create-firefly)
   })


(defn mouse-x
  []
  (if (= (q/mouse-x) 0)
    (/ (q/width) 2)
    (q/mouse-x))
  )

(defn mouse-y
  []
  (if (= (q/mouse-y) 0)
    (/ (q/height) 2)
    (q/mouse-y))
  )

(defn setup []
  (q/smooth)
  (q/frame-rate 30)
  (q/cursor-image (q/load-image "resources/images/cursor.png"))
  (generate-fireflies 40)
  )

(defn draw-firefly
  ;; [[[x y] on firefly_graphic]]
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
    ;;   (q/image firefly_graphic x y)
    (do
      (q/fill 0 64 0 128)
      (q/ellipse x y 20 20))))

(defn draw [state]
  (q/background 0)
  (q/no-stroke)

  ;; (doseq [firefly (map vector (:fireflies state) (:on state) (repeatedly (fn [] (:firefly_graphic state))))]
  (doseq [firefly (map vector (:fireflies state) (:on state))]
    (draw-firefly firefly)))

(defn update-acceleration
  [[x y] [ax ay]]

  [(* 0.0002 (- (mouse-x) x))
   (* 0.0002 (- (mouse-y) y))])

(defn update-velocity
  [[x y] [vx vy] [ax ay]]

  (let [new-vx (+ vx ax)
        new-vy (+ vy ay)
        distance-x (q/abs (- (mouse-x) x))
        distance-y (q/abs (- (mouse-y) y))]
    [(q/constrain new-vx (- distance-x) distance-x)
     (q/constrain new-vy (- distance-y) distance-y)]))

(defn update-firefly
  [[x y] [vx vy]]

  [(+ x vx) (+ y vy)])

(defn update-on
  [on]
  (if on
    (< (q/random 1000) 950)
    (< (q/random 100) 5)))

(defn update
  [state]

  ;; If the absolute value of the velocity in both x and y directions gets
  ;; very small we can assume the mouse is not moving and we need to randomly
  ;; disperse the fireflies to keep the sketch active even if no one is
  ;; interacting with it.
  (if (every? (fn [[vx vy]] (and (< (q/abs vx) 0.01) (< (q/abs vy) 0.01))) (:velocity state))
    (let [new_fireflies (generate-fireflies 40)]
      (assoc state :fireflies (:fireflies new_fireflies)
             :velocity (:velocity new_fireflies)
             :acceleration (:acceleration new_fireflies)
             :on (:on new_fireflies))
      )
    (assoc state :fireflies (into [] (map update-firefly (:fireflies state) (:velocity state)))
           :velocity (into [] (map update-velocity (:fireflies state) (:velocity state) (:acceleration state)))
           :acceleration (into [] (map update-acceleration (:fireflies state) (:acceleration state)))
           :on (into [] (map update-on (:on state)))
           )))

(q/defsketch practice
  :title "Fireflies"
  :size :fullscreen
  :setup setup
  :draw draw
  :update update
  :features [:keep-on-top]
  :middleware [m/fun-mode])

