SELECT maker, AVG(screen)
FROM product JOIN laptop
ON product.model = laptop.model
GROUP BY maker;
