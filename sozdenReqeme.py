class Solution:
    def sozdenReqeme(self,s):
        sozler = {'sıfır': 0,
                'bir': 1,
                'iki': 2,
                'üç': 3,
                'dörd': 4,
                'beş': 5,
                'altı': 6,
                'yeddi': 7,
                'səkkiz': 8,
                'doqquz': 9,
                'on': 10,
                'iyirmi': 20,
                'otuz': 30,
                'qırx': 40,
                'əlli': 50,
                'altmış': 60,
                'yetmiş': 70,
                'səksən': 80,
                'doxsan': 90,
                  }
    
        ls=[]
        for i in s.split(' '):
            if i in sozler:
                ls.append(sozler[i])
            elif i=="yüz":
                ls[-1]*=100
            elif i=="min":
                ls=[i*1000 for i in ls]
            elif i=="milyon":
                ls=[i*1000000 for i in ls]
        return sum(ls)
p=Solution()
print(p.sozdenReqeme("altı min iyirmi üç"))
