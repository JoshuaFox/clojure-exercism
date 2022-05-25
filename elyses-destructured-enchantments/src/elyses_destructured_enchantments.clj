(ns elyses-destructured-enchantments)

(defn first-card
  "Returns the first card from deck."
  [deck]
  (let [[frst ] deck]  frst))



(defn second-card
  "Returns the second card from deck."
  [deck]
  (let [[_  secnd ] deck]  secnd))


(defn swap-top-two-cards
  "Returns the deck with first two items reversed."
  [deck]
  (let [[frst secnd & rest] deck]
    (concat [secnd frst] rest)))

(defn discard-top-card
  "Returns a vector containing the first card and
   a vector of the remaining cards in the deck."
  [deck]
  (let [[frst & rest ] deck]
    [frst (if (empty? rest)
            nil
            (vec rest))]))

(discard-top-card [ 3 4 5 6 7])

(def face-cards
  ["jack" "queen" "king"])

(defn insert-face-cards
  "Returns the deck with face cards between its head and tail."
  [deck]
  (let [[frst & rst ] deck]
    (filter identity
            (concat (into [frst] face-cards) rst))))

