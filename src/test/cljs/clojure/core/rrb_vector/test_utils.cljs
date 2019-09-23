(ns clojure.core.rrb-vector.test-utils
  (:require [clojure.core.rrb-vector.rrbt :as rrbt]))

;; Parts of this file are nearly identical to
;; src/test/clojure/clojure/core/rrb_vector/test_utils.clj, but also
;; significant parts are specific to each of the clj/cljs versions, so
;; while they could later be combined into a .cljc file, it may not
;; give much benefit to do so.

(def extra-checks? false)

(defn reset-optimizer-counts! []
  (println "reset all optimizer counts to 0")
  (reset! rrbt/peephole-optimization-count 0)
  (reset! rrbt/fallback-to-slow-splice-count1 0)
  (reset! rrbt/fallback-to-slow-splice-count2 0))

(defn print-optimizer-counts []
  (println "optimizer counts: peephole=" @rrbt/peephole-optimization-count
           "fallback1=" @rrbt/fallback-to-slow-splice-count1
           "fallback2=" @rrbt/fallback-to-slow-splice-count2))

;; Enable tests to be run on versions of Clojure before 1.10, when
;; ex-message was added.

(defn ex-message-copy
  "Returns the message attached to the given Error / ExceptionInfo object.
  For non-Errors returns nil."
  [ex]
  (when (instance? js/Error ex)
    (.-message ex)))

(defn ex-cause-copy
  "Returns exception cause (an Error / ExceptionInfo) if ex is an
  ExceptionInfo.
  Otherwise returns nil."
  [ex]
  (when (instance? ExceptionInfo ex)
    (.-cause ex)))
