SELECT pc1.model model1, pc2.model model2,  pc1.speed, pc1.ram 
FROM pc pc1 JOIN pc pc2 
ON pc1.speed=pc2.speed AND  pc1.code<>pc2.code AND pc1.model>pc2.model;