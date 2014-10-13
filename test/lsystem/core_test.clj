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

;; apply-rules ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Identity
(expect [] (apply-rules test-rules []))

;; Non matching rules give us identity
(expect [\a] (apply-rules test-rules [\a]))
(expect (vec "ab") (apply-rules test-rules (vec "ab")))
(expect (vec "abba") (apply-rules test-rules (vec "abba")))

;; Clearing rules
(expect [] (apply-rules test-rules [\c] ))
(expect [] (apply-rules test-rules [\c \c]))
(expect [\a] (apply-rules test-rules [\c \a \c]))

;; expanding rules
(expect (vec "fffggg") (apply-rules test-rules [\f \g]))

;; all at once
(expect (vec "gggafff") (apply-rules test-rules [\g \a \c \f]))

;; lazy iteration helper
(expect `([\h]
         [\f \c \h]
         [\f \f \f \f \c \h]
         [\f \f \f \f \f \f \f \f \f \f \f \f \f \c \h])
        (take 4 (iterations test-rules [\h])))
