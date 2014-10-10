(ns lsystem.core
  (:refer-clojure :exclude [iterate]))

(def sierpinski {\F "F−G+F+G−F"
                 \G "GG"})

(defn parse-symbol
  [rules symbol]
  (get rules symbol [symbol]))

(defn iterate [rules axiom]
  (let [parse-symbol-with-rules (partial parse-symbol rules)]
    (mapcat parse-symbol-with-rules axiom)))
