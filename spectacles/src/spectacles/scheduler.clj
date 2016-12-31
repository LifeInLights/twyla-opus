(ns spectacles.scheduler
  (:require [spectacles.present-logo]
            [spectacles.starfield]
            [spectacles.bet-you-miss]
            [spectacles.cyberspeed]
            [spectacles.kaleidoscope-logo]
            [spectacles.kaleidoscope]
            [spectacles.whalesong]
            [spectacles.fireflies]
            [spectacles.polkadots]
            [spectacles.simple-camera]
            [spectacles.mirror-camera]))

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

               :kaleidoscope {:setup  (partial spectacles.kaleidoscope/setup "resources/images/lifeinlights-logo.png")
                              :update spectacles.kaleidoscope/update
                              :draw   spectacles.kaleidoscope/draw
                              :name   "Kaleidoscope"
                              :author "Arthur Hall III"}

               :kaleidoscope-clover {:setup  (partial spectacles.kaleidoscope/setup "resources/images/clover.png")
                                     :update spectacles.kaleidoscope/update
                                     :draw   spectacles.kaleidoscope/draw
                                     :name   "Kaleidoscope Clover"
                                     :author "Arthur Hall III"}

               :kaleidoscope-lamp {:setup  (partial spectacles.kaleidoscope/setup "resources/images/lamp-logo-silhouette.png")
                                   :update spectacles.kaleidoscope/update
                                   :draw   spectacles.kaleidoscope/draw
                                   :name   "Kaleidoscope Lamp Silhouette"
                                   :author "Arthur Hall III"}

               :kaleidoscope-logo {:setup  (partial spectacles.kaleidoscope-logo/setup "resources/images/lifeinlights-logo.png")
                                   :update spectacles.kaleidoscope-logo/update
                                   :draw   spectacles.kaleidoscope-logo/draw
                                   :name   "Kaleidoscope Logo"
                                   :author "Arthur Hall III"}

               :kaleidoscope-spiral-clover {:setup  (partial spectacles.kaleidoscope-logo/setup "resources/images/clover.png")
                                            :update spectacles.kaleidoscope-logo/update
                                            :draw   spectacles.kaleidoscope-logo/draw
                                            :name   "Kaleidoscope Spiral Clover"
                                            :author "Arthur Hall III"}

               :whalesong {:setup  spectacles.whalesong/setup
                           :update spectacles.whalesong/update
                           :draw   spectacles.whalesong/draw
                           :name   "Whalesong"
                           :author "Aaron Arnett"}

               :fireflies {:setup  spectacles.fireflies/setup
                           :update spectacles.fireflies/update
                           :draw   spectacles.fireflies/draw
                           :name   "Fireflies"
                           :author "Arthur Hall III"}

               :polkadots {:setup  spectacles.polkadots/setup
                           :update spectacles.polkadots/update
                           :draw   spectacles.polkadots/draw
                           :name   "Marching Polka Dots"
                           :author "Arthur Hall III"}

               :simple-camera {:setup  spectacles.simple-camera/setup
                               :update spectacles.simple-camera/update
                               :draw   spectacles.simple-camera/draw
                               :name   "SimpleCam"
                               :author "Aaron Arnett"}

               :social-light {:setup  spectacles.social-light/setup
                              :update spectacles.social-light/update
                              :draw   spectacles.social-light/draw
                              :name   "SocialLight"
                              :author "Aaron Arnett"}

               :mirror-camera {:setup  spectacles.mirror-camera/setup
                               :update spectacles.mirror-camera/update
                               :draw   spectacles.mirror-camera/draw
                               :name   "MirrorCam"
                               :author "Aaron Arnett"}})

(def installations {:lifeinlights01 :logo-countdown       ;; Leeds north door
                    :lifeinlights02 :social-light         ;; Commercial Kitchen (window)
                    :lifeinlights03 :bet-you-miss         ;; Easy Pickins 
                    :lifeinlights04 :cyberspeed           ;; Bridges and Lane
                    :lifeinlights05 :social-light         ;; Kerr (C)
                    :lifeinlights06 :fireflies            ;; Leeds lobby (or entrance?)
                    :lifeinlights07 :kaleidoscope-lamp    ;; Commercial Kitchen (facade) (800x600)
                    :lifeinlights08 :polkadots            ;; Kerr (A)
                    :lifeinlights09 :polkadots            ;; Kerr (B)
                    :lifeinlights10 :logo-countdown       ;; Unused
                    })

(defn select-sketch []
  (let [hostname (.getHostName (java.net.InetAddress/getLocalHost))]
    (get sketches (get installations (keyword hostname) :kaleidoscope-lamp))))

(def active-sketch (atom {:sketch (select-sketch)
                          :expiration :never}))

(defn setup []
  ((:setup (:sketch @active-sketch))))

(defn update [state]
  ((:update (:sketch @active-sketch)) state))

(defn draw [state]
  ((:draw (:sketch @active-sketch)) state))
