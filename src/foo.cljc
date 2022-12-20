(ns foo
  (:require
   [malli.experimental :as mx]))

(mx/defn foo
  [_a :- [:map [:keys [:+ :keyword]]]
   _b :- :any])
