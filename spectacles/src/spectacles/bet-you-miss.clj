(ns spectacles.bet-you-miss
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  ; Set frame rate to 3 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:color 0
   :angle 0})

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  {:color (mod (+ (:color state) 0.7) 255)
   :angle (+ (:angle state) 0.2)})

(defn draw-state [state]
  ; Clear the sketch by filling it with light-grey color.
  (q/background 240)
  ; Set circle color.
  (q/fill (:color state) 255 255)
  ; Calculate x and y coordinates of the circle.
  (let [angle (:angle state)
        x (* 125 (q/sin angle))
        y (* 125 (q/cos angle))]
    ; Move origin point to the center of the sketch.
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ; Draw the circle.
      (q/ellipse x y 100 100)
      (q/fill 255 (:color state) 255)
      (q/ellipse x y 75 75)
      (q/fill 255 255 (:color state))
      (q/ellipse x y 50 50)
      (q/fill (:color state) 255 255)
      (q/ellipse x y 25 25)
      (q/ellipse x y 1 1)))


  (let [angle (:angle state)
        x (* 1 (q/sin angle))
        y (* 1 (q/cos angle))]
    ; Move origin point to the center of the sketch.
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ; Draw the circle.
      (q/ellipse x y 100 100)
      (q/fill 255 (:color state) 255)
      (q/ellipse x y 75 75)
      (q/fill 255 255 (:color state))
      (q/ellipse x y 50 50)
      (q/fill (:color state) 255 255)
      (q/ellipse x y 25 25)
      (q/ellipse x y 1 1)))

  (let [angle (:angle state)
        x (* 175 (q/cos angle))
        y (* 175 (q/sin angle))]
    ; Move origin point to the center of the sketch.
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ; Draw the circle.
      (q/ellipse x y 100 100)
      (q/fill 255 (:color state) 255)
      (q/ellipse x y 75 75)
      (q/fill 255 255 (:color state))
      (q/ellipse x y 50 50)
      (q/fill (:color state) 255 255)
      (q/ellipse x y 25 25)
      (q/ellipse x y 1 1)))

  (let [angle (:angle state)
        x (* 175 (q/cos angle))
        y (* 175 (q/cos angle))]
    ; Move origin point to the center of the sketch.
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ; Draw the circle.
      (q/ellipse x y 100 100)
      (q/fill 255 (:color state) 255)
      (q/ellipse x y 75 75)
      (q/fill 255 255 (:color state))
      (q/ellipse x y 50 50)
      (q/fill (:color state) 255 255)
      (q/ellipse x y 25 25)
      (q/ellipse x y 1 1)))

  (let [angle (:angle state)
        x (* -175 (q/sin angle))
        y (* 175 (q/sin angle))]
    ; Move origin point to the center of the sketch.
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ; Draw the circle.
      (q/ellipse x y 100 100)
      (q/fill 255 (:color state) 255)
      (q/ellipse x y 75 75)
      (q/fill 255 255 (:color state))
      (q/ellipse x y 50 50)
      (q/fill (:color state) 255 255)
      (q/ellipse x y 25 25)
      (q/ellipse x y 1 1)))
  )

;; LifeInLights sketch
;; Artist: Galen Arnett
;; Date Created: 2016-01-19

;; (q/defsketch bet-you-miss
;;   :title "Bet You Miss - Galen Arnett"
;;   :size [500 500]
;;   ; setup function called only once, during sketch initialization.
;;   :setup setup
;;   ; update-state is called on each iteration before draw-state.
;;   :update update-state
;;   :draw draw-state
;;   :features [:keep-on-top]
;;   ; This sketch uses functional-mode middleware.
;;   ; Check quil wiki for more info about middlewares and particularly
;;   ; fun-mode.
;;   :middleware [m/fun-mode])

