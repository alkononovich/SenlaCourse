SELECT DISTINCT maker
FROM Product 
WHERE type = 'printer' AND 
maker IN(SELECT maker FROM Product 
WHERE model IN(SELECT model FROM PC
WHERE speed = (SELECT MAX(speed)
FROM (SELECT speed FROM PC 
WHERE ram=(SELECT MIN(ram)FROM PC)) as1)));