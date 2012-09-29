(defproject clojure-tictactoe "0.0"
  :description "tic-tac-toe application"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [speclj "2.2.0"]]
  :plugins [[speclj "2.2.0"]]
  :main ^:skip-aot tictactoe.runner
  ;:main tictactoe.runner
  :test-paths ["spec/"]
  :java-source-path "src/")
