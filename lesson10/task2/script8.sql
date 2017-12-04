SELECT maker 
FROM product 
WHERE (maker  NOT IN (SELECT maker FROM product WHERE type IN ('Laptop'))) AND (maker IN (SELECT maker FROM product WHERE type IN ('PC')));
