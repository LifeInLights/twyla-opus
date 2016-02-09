(ns spectacles.scheduler
  (:require [spectacles.present-logo]
            [spectacles.starfield]
            [spectacles.bet-you-miss]
            [spectacles.cyberspeed]
            [spectacles.kaleidoscope-logo]
            [spectacles.kaleidoscope]
            [clojure.core.async :as async]))

(def sketches {:logo-countdown {:setup  spectacles.present-logo/setup
                                :update spectacles.present-logo/update
                                :draw   spectacles.present-logo/draw
                                :name   "Life In Lights Countdown"
                                :author "Aaron Arnett"}

               :starfield {:setup  spectacles.starfield/setup
                           :update spectacles.starfield/update-state
                           :draw   spectacles.starfield/draw-state
                           :name   "Starfield"
                           :author "Arthur Hall III"}

               :bet-you-miss {:setup  spectacles.bet-you-miss/setup
                              :update spectacles.bet-you-miss/update-state
                              :draw   spectacles.bet-you-miss/draw-state
                              :name   "Bet You Miss"
                              :author "Galen Arnett"}

               :cyberspeed {:setup  spectacles.cyberspeed/setup
                            :update spectacles.cyberspeed/update
                            :draw   spectacles.cyberspeed/draw
                            :name   "Cyber Speed"
                            :author "Maria Jackson"}

               :kaleidoscope {:setup  spectacles.kaleidoscope/setup
                              :update spectacles.kaleidoscope/update
                              :draw   spectacles.kaleidoscope/draw
                              :name   "Kaleidoscope"
                              :author "Arthur Hall III"}

               :kaleidoscope-logo {:setup  spectacles.kaleidoscope-logo/setup
                                   :update spectacles.kaleidoscope-logo/update
                                   :draw   spectacles.kaleidoscope-logo/draw
                                   :name   "Kaleidoscope Logo"
                                   :author "Arthur Hall III"}})

(def installations {:lifeinlights01 :logo-countdown
                    :lifeinlights02 :starfield
                    :lifeinlights03 :bet-you-miss
                    :lifeinlights04 :cyberspeed
                    :lifeinlights05 :kaleidoscope
                    :lifeinlights06 :kaleidoscope-logo})

(defn select-sketch []
  (let [hostname (.getHostName (java.net.InetAddress/getLocalHost))]
    (get sketches (get installations (keyword hostname) :logo-countdown))))

(def active-sketch (atom {:sketch (select-sketch)
                          :expiration :never}))

(defn setup []
;;  (println "Calling setup on: " (get-in @active-sketch [:sketch :name]))
  ;; determine which is the first sketch to be active and invoke its setup function
  ((:setup (:sketch @active-sketch))))

(defn update [state]
;;  (println "Calling update on: " (get-in @active-sketch [:sketch :name]))
  ;; determine whether it's time to swap sketches
  ;; if not, invoke the active sketch's draw function
  ;; if so, swap to the new sketch's draw function, initializing with its setup function
  ((:update (:sketch @active-sketch)) state))

(defn draw [state]
;;  (println "Calling draw on: " (get-in @active-sketch [:sketch :name]))
  ;; invoke the active sketch's draw function
  ((:draw (:sketch @active-sketch)) state))
