(ns hosp-finder.core
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.string :as s])
  (:use (edu.ucdenver.ccp.kr kb rdf sparql)
        edu.ucdenver.ccp.kr.sesame.kb))

(defn extract-maps-from-csv [[column-names & rows]]
  (let [col-as-keywords (map (fn [name] (-> name
                                           s/trim
                                           (s/replace " " "-")))
                             column-names)]
    (map #(let [row (zipmap col-as-keywords %)
                [code def] (-> (get row "DRG-Definition")
                             (s/split #" - "))]
            (assoc row
              "DRG-Code" (Integer/parseInt code)
              "DRG-Definition" def))
         rows)))

(def our-namespace "http://sssw.org/mini-projects/nn1/onto/")

(defn read-csv [f]
  (csv/read-csv (io/reader f)))


(def t-store
  (register-namespaces (kb :sesame-mem)
                       {"providers" (str our-namespace "providers#")
                        "drg-codes" (str our-namespace "drg-codes#")
                        "predicates" (str our-namespace "predicates#")}))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
