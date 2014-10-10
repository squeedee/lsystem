;; TODO: dev profile with expectations in it
(defproject lsystem "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [expectations "2.0.12"]]
  :plugins [[lein-expectations "0.0.7"]
            [lein-autoexpect "1.3.0"]])
