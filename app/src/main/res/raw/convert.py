#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun May  5 01:11:21 2019

@author: javi
"""

leveltozero=True

file1=open("words.csv","r")
file2=open("ddbb.sql","w")
n=0
nm=0
values=[]
valuesNote=[]

for line in file1:
    data=line.replace("\n","").replace("'","\'").split("|")
    if len(data)==5:
      data[0]="'"+data[0].strip().lower()+"'"
      data[1]="'"+data[1].strip()+"'"
      data[2]="'"+data[2].strip()+"'"
      data[3]="'"+data[3].strip()+"'"
      if leveltozero:
              data[4]="1"
      else:
              data[4]=data[4].strip()
      dat=["("+data[0],data[1],data[2],data[3],data[4]+")"]
      values.append(",".join(dat))
      n+=1
    elif len(data)==2:
      data[0]="'"+data[0].strip().lower()+"'"
      data[1]="'"+data[1].strip()+"'"
      dat=["("+data[0],data[1]+")"]
      valuesNote.append(",".join(dat))
      nm+=1
    if len(values)%99==0 and n>0:
        query="INSERT INTO WORDS (word, example, meaning, syntaxis,category) VALUES "+",".join(values)+";"
        file2.write(query+"\n")
        values=[]
    if len(valuesNote)%99==0 and nm>0:
        queryNote="INSERT INTO NOTES (title, note) VALUES  "+",".join(valuesNote)+";"
        file2.write(queryNote+"\n")
        valuesNote=[]

if len(values)>0:
        query="INSERT INTO WORDS (word, example, meaning, syntaxis,category) VALUES "+",".join(values)+";"
        file2.write(query+"\n")
if len(valuesNote)>0:
        queryNote="INSERT INTO NOTES (title, note) VALUES  "+",".join(valuesNote)+";"
        file2.write(queryNote+"\n")  
file1.close()
print(n,"words")
print(nm,"notes")
file2.close()




