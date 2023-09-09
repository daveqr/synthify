(ns synthify.dd.file1
  (:require [java-time.api :as t]))

(defn print-current-time
  []
  (let [current-time (t/local-date)]
    (println "Current time: " current-time)))

(print-current-time)