(ns lsystem.core)

(defn parse-symbol
  [rules symbol]
  (get rules symbol [symbol]))

(defn apply-rules [rules axiom]
    (let [parse-symbol-with-rules (partial parse-symbol rules)]
      (mapcat parse-symbol-with-rules axiom)))

(defn iterations [rules axiom]
  (iterate #(apply-rules rules %) axiom))

;; Sierpinksi Demo

(def sierpinski-rules {\F "F−G+F+G−F"
                       \G "GG"})

(def sierpinski
  (iterations sierpinski-rules [\F]))
