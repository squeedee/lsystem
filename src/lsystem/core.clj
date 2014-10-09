(ns lsystem.core)

(def sierpinski {\F "F−G+F+G−F"
                 \G "GG"})

(defn parse-symbol
  [rules symbol]
  (get rules symbol symbol))
