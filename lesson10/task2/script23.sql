SELECT DISTINCT maker 
FROM product JOIN pc  
ON product.model = pc.model
WHERE speed >= 750 AND maker IN 
(SELECT maker 
	FROM product JOIN laptop  
    ON laptop.model = product.model 
    WHERE speed >= 750)
GROUP BY maker;