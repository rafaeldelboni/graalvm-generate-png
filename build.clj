(ns build
  (:refer-clojure :exclude [test])
  (:require [org.corfield.build :as bb]))

(def lib 'net.clojars.rafaeldelboni/graalvm-generate-png)
(def version "0.1.0-SNAPSHOT")
(def main 'rafaeldelboni.graalvm-generate-png)

(defn test "Run the tests." [opts]
  (bb/run-tests opts))

(defn ci "Run the CI pipeline of tests (and build the uberjar)." [opts]
  (-> opts
      (assoc :lib lib
             :version version
             :main main
             :basis (update (bb/default-basis) :aliases concat [:native]))
      (bb/run-tests)
      (bb/clean)
      (bb/uber)))
