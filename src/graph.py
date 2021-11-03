# # !/usr/bin/python
# # -*- coding: utf-8 -*-
import matplotlib.pyplot as plt

from sort import sort_insert, sort_quick_recursion

lenArr = [100, 300, 500, 700, 900]

# sort_bubble = [0.0004, 0.0025, 0.0072, 0.0152, 0.0253]
# sort_insert = [0.0, 0.0, 0.0001, 0.0001, 0.0001]
# sort_quick_recursion = [0.0, 0.0, 0.0003, 0.0005, 0.0006]

# sort_bubble = [0.0003, 0.0034, 0.0102, 0.0214, 0.0389]
# sort_quick_recursion = [0.0, 0.0, 0.0002, 0.0, 0.0003]
# sort_insert = [0.0003, 0.0034, 0.0098, 0.0191, 0.0284]

sort_bubble = [0.0013, 0.0092, 0.0298, 0.0737, 0.1311]
sort_quick_recursion = [0.0006, 0.0084, 0.0222, 0.0381, 0.1005]
sort_insert =  [0.0008, 0.0088, 0.0242, 0.0511, 0.1159]



fig, ax = plt.subplots()

ax.plot(lenArr, sort_bubble, label="Выбором", color='green')

ax.plot(lenArr, sort_insert, label="Вставками", color='red')

ax.plot(lenArr, sort_quick_recursion, label="Быстрая", color='blue')

ax.legend()
ax.grid()

ax.set_xlabel('Размерность')
ax.set_ylabel('Время (с)')

plt.show()
