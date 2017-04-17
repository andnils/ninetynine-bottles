(ns ninetynine.bottles
  (:require [clojure.string :as s]))


(declare make-bottle)

(defprotocol Bottle
  (desc [this])
  (action [this])
  (successor [this]))

(defrecord DefaultBottle [i]
  Bottle
  (desc [this] (str i " bottles"))
  (action [this] "Take one down and pass it around")
  (successor [this] (make-bottle (dec i))))

(defrecord Bottle0 []
  Bottle
  (desc [this] "no more bottles")
  (action [this] "Go to the store and buy some more")
  (successor [this] (make-bottle 99)))

(defrecord Bottle1 []
  Bottle
  (desc [this] "1 bottle")
  (action [this]  "Take it down and pass it around")
  (successor [this] (make-bottle 0)))

(defrecord Bottle6 []
  Bottle
  (desc [this] "1 six-pack")
  (action [this]  "Take it down and pass it around")
  (successor [this] (make-bottle 5)))

(defn make-bottle [i]
  (case i
    0 (->Bottle0)
    1 (->Bottle1)
    ;;   6 (->Bottle6)
    (->DefaultBottle i)))



(defn verse [i]
  (let [bottle (make-bottle i)]
    (str (s/capitalize (desc bottle)) " of beer on the wall, "
         (desc bottle) " of beer.\n"
         (action bottle) ", "
         (desc (successor bottle)) " of beer on the wall.\n")))

(defn verses [starting ending]
  (let [verse-numbers (range starting (dec ending) -1)]
    (s/join "\n" (map verse verse-numbers))))

(defn song []
  (verses 99 0))
