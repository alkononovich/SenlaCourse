SELECT maker, price 
FROM product JOIN 
(SELECT model, price FROM printer 
WHERE (price=(SELECT(MIN(price)) FROM printer WHERE color = 'y'))) p 
ON p.model=product.model;