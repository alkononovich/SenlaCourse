SELECT maker, MAX(c) 
FROM product
JOIN (SELECT MAX(price) c, model
FROM pc
GROUP BY model) p 
ON p.model=product.model
GROUP BY maker;