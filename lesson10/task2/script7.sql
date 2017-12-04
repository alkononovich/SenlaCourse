SELECT product.model,price,product.type FROM pc INNER JOIN product ON product.model=pc.model WHERE product.maker='B' UNION 
SELECT product.model,price,product.type FROM laptop INNER JOIN product ON product.model=laptop.model WHERE product.maker='B' UNION 
SELECT product.model,price,product.type FROM printer INNER JOIN product ON product.model=printer.model WHERE product.maker='B';