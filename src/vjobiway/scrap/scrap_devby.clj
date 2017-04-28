(ns vjobiway.scrap.scrap-devby
  (:require [jsoup.soup :as $]
            [clj-time.core :as t]
            [clj-time.format :refer [parse formatter formatters]]))

(def TITLE_SELECTOR "body > div.wrapper.dev.all > div:nth-child(3) > div.dev-all > div.dev-main > div > div.dev-center.nobrd.jobs-show > div > div.dev-left.col1.nopd.body-jobs > div > div.block.data-card > div > div > div.panel-top.js-jobs-show-sticker-offset > h3")
(def DESCRIPTION_SELECTOR "body > div.wrapper.dev.all > div:nth-child(3) > div.dev-all > div.dev-main > div > div.dev-center.nobrd.jobs-show > div > div.dev-left.col1.nopd.body-jobs > div > div.block.data-info > div.bl.data-desc > div")
(def COMPANY_SELECTOR "body > div.wrapper.dev.all > div:nth-child(3) > div.dev-all > div.dev-main > div > div.dev-center.nobrd.jobs-show > div > div.dev-left.col1.nopd.body-jobs > div > div.block.data-card > div > div > div.panel-top.js-jobs-show-sticker-offset > a")
(def TYPE_SELECTOR "body > div.wrapper.dev.all > div:nth-child(3) > div.dev-all > div.dev-main > div > div.dev-center.nobrd.jobs-show > div > div.dev-left.col1.nopd.body-jobs > div > div.block.data-card > div > div > div.panel-down > div.left > span:nth-child(1)")
(def CITY_SELECTOR "body > div.wrapper.dev.all > div:nth-child(3) > div.dev-all > div.dev-main > div > div.dev-center.nobrd.jobs-show > div > div.dev-left.col1.nopd.body-jobs > div > div.block.data-card > div > div > div.panel-down > div.right > span:nth-child(2)")
(def DUE_TO_SELECTOR "body > div.wrapper.dev.all > div:nth-child(3) > div.dev-all > div.dev-main > div > div.dev-center.nobrd.jobs-show > div > div.dev-left.col1.nopd.body-jobs > div > div.block.data-card > div > div > div.panel-down > div.right > span:nth-child(3) > time")
(def LEVEL_SELECTOR "body > div.wrapper.dev.all > div:nth-child(3) > div.dev-all > div.dev-main > div > div.dev-center.nobrd.jobs-show > div > div.dev-left.col1.nopd.body-jobs > div > div.block.data-card > div > div > div.panel-down > div.left > span:nth-child(2)")
(def SKILLS_SELECTOR "body > div.wrapper.dev.all > div:nth-child(3) > div.dev-all > div.dev-main > div > div.dev-center.nobrd.jobs-show > div > div.dev-left.col1.nopd.body-jobs > div > div.block.data-card > div > div > div.panel-middle > ul > li")

(defn texts [selector doc]
  (->
   ($/select selector doc)
   ($/text)))

(defn text [selector doc]
  (first (texts selector doc)))

(defn custom-format []
  (formatter "yyyy-MM-dd'T'HH:mm:ss+HH:mm"))

(defn get-time [doc]
  (first ($/attr "datetime" ($/select DUE_TO_SELECTOR doc))))

(defn parse-vacancy-page [html]
  (let [doc ($/parse html)]
    {:title (text TITLE_SELECTOR doc)
     :description (text DESCRIPTION_SELECTOR doc)
     :company (text COMPANY_SELECTOR doc)
     :city (text CITY_SELECTOR doc)
     :dueTo (parse (custom-format) (get-time doc))
     :type (text TYPE_SELECTOR doc)
     :level (text LEVEL_SELECTOR doc)
     :skills (map #(.text %) ($/select SKILLS_SELECTOR doc))}))

