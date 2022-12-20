(ns bar
  (:require
   [foo :as foo]))

(defn bar
  []
  (foo/foo
   {:keys [:a]}
   nil))
