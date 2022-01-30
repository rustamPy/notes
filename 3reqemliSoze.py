class Solution:
    def reqemdenSoze(self,num,join=True):
        reqemle = {0:'sıfır',
                    1:'bir',
                    2:'iki',
                    3:'üç',
                    4:'dörd',
                    5:'beş',
                    6:'altı',
                    7:'yeddi',
                    8:'səkkiz',
                    9:'doqquz',
                    10:'on',
                    20:'iyirmi',
                    30:'otuz',
                    40:'qırx',
                    50:'əlli',
                    60:'altmış',
                    70:'yetmiş',
                    80:'səksən',
                    90:'doğsan',
                      }
        
        sozle = []
        if len(str(num))==1:
            sozle.append(reqemle[num])
            
        if len(str(num))==2:
            if str(num)[-1]!="0":
                cem=reqemle[int(str(num)[0]+"0")]+" "+reqemle[int(str(num)[1])]
                sozle.append(cem)
            else:
                sozle.append(reqemle[int(str(num)[0]+"0")])
            
        if len(str(num))==3:
            if str(num)[-1]!="0" and str(num)[-2]!="0":
                cem=reqemle[int(str(num)[0])]+" yüz "+reqemle[int(str(num)[1]+"0")]+" "+reqemle[int(str(num)[-1])]
                sozle.append(cem)
            elif str(num)[-1]=="0":
                sozle.append(reqemle[int(str(num)[0])]+" yüz "+reqemle[int(str(num)[1]+"0")])
            else:
                sozle.append(reqemle[int(str(num)[0])]+" yüz "+reqemle[int(str(num)[-1])])
        
            
        return "".join(sozle)
p=Solution()
print(p.reqemdenSoze(123))
