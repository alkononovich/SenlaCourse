SELECT maker, COUNT(model) 
FROM product
GROUP BY maker 
HAVING COUNT(model) >=3;