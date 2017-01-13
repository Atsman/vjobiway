(ns vjobiway.web.layout
  (:require [hiccup.core :refer :all]))

(defn layout
  [child]
    (html 
      [:html
        [:head 
          [:link {:rel "stylesheet" :href "public/style.css"}]]
        [:body child]]))

(defn header []
  (html
    [:div.header-container
      [:header.header
        [:div.header__logo]
        [:nav.header__nav
          [:a.header__item {:href "/"}]]]]))

(defn main-layout
  [child]
    (layout (html 
              [:div.layout
                (header)])))

