(ns core
  (:require
   [malli.core :as m]
   [malli.experimental :as mx]
   [malli.dev :as dev]))

;; original problem
(mx/defn foo
  [_a :- [:map [:keys [:+ :keyword]]]
   _b :- :any])

(defn case-1 [_x])
(m/=> case-1 [:=>
              [:cat [:map [:keys [:+ :keyword]]]]
              :nil])

;; malli error since should be 1 or more
;; clj-kondo ok
(case-1 {:keys []})
;; everything ok
(case-1 {:keys [:keyword]})
;; malli error since not a keyword
(case-1 {:keys [1]})

(defn case-2 [_x])
(m/=> case-2 [:=>
              [:cat [:map [:keys [:? :int]]]]
              :nil])

;; clj-kondo ok
(case-2 {:keys []})
;; everything ok
(case-2 {:keys [1]})
;; malli error since more than 1
(case-2 {:keys [1 1]})

(defn case-3 [_x])
(m/=> case-3 [:=>
              [:cat [:map [:keys [:* :string]]]]
              :nil])

;; malli ok
(case-3 {:keys []})
(case-3 {:keys ["b"]})
(case-3 {:keys ["a" "b"]})
;; clj-kondo not-ok
(case-3 {:keys [:a]})
(case-3 {:keys [1 1]})

(defn run
  "Generate clj-kondo types"
  [_]
  (dev/start!))
