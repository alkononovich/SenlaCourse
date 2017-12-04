SELECT AVG(speed) 
FROM pc INNER JOIN product ON pc.model=product.model 
WHERE maker='A';