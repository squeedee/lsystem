(ns lsystem.core-test
  (:refer-clojure :exclude [iterate])
  (:require [lsystem.core :refer :all]
            [expectations :refer :all]))

(def test-rules {\f (vec "fff")
                 \g (vec "ggg")
                 \h (vec "fch")
                 \c []
                 })

;; Defined rules are locatable
(expect (vec "fff") (parse-symbol test-rules \f))
(expect [] (parse-symbol test-rules \c))

;; Undefined rules return the lookup symbol
;;   -> lets the l-system not need to define constants but
;;      does require 'clearing rules'
(expect [\z] (parse-symbol test-rules \z))

;; iterate ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Identity
(expect [] (iterate test-rules []))

;; Non matching rules give us identity
(expect [\a] (iterate test-rules [\a]))
(expect (vec "ab") (iterate test-rules (vec "ab")))
(expect (vec "abba") (iterate test-rules (vec "abba")))

;; Clearing rules
(expect [] (iterate test-rules [\c] ))
(expect [] (iterate test-rules [\c \c]))
(expect [\a] (iterate test-rules [\c \a \c]))

;; expanding rules
(expect (vec "fffggg") (iterate test-rules [\f \g]))

;; all at once
(expect (vec "gggafff") (iterate test-rules [\g \a \c \f]))

;; recur once is the same as not providing the param
(expect (vec "gggafff") (iterate test-rules [\g \a \c \f] 1))

;; can itterate many times
(expect (vec "fch") (iterate test-rules [\h] 1))
(expect (vec "ffffch") (iterate test-rules [\h] 2))
(expect (vec "fffffffffffffch") (iterate test-rules [\h] 3))
