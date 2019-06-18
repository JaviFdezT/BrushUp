#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun May  5 01:11:21 2019

@author: javi
"""

leveltozero=True

file1=open("ddbb.csv","r")
file2=open("ddbb.sql","w")
n=0
nm=0
query=""
queryNote=""

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
      dat=[data[0],data[1],data[2],data[3],data[4]]
      query=query+",".join(dat)+"),("
      n+=1
    elif len(data)==2:
      data[0]="'"+data[0].strip().lower()+"'"
      data[1]="'"+data[1].strip()+"'"
      dat=[data[0],data[1]]
      queryNote=queryNote+",".join(dat)+"),("
      nm+=1
    
file1.close()
if n>0:
   query="INSERT INTO WORDS (word, example, meaning, syntaxis,category) VALUES ("+query+")))"
   query=query.replace(",()))","")
   file2.write(query+"\n")
   print(n,"words")
if nm>0:
   queryNote="INSERT INTO NOTES (title, note) VALUES ("+queryNote+")))"
   queryNote=queryNote.replace(",()))","")
   file2.write(queryNote+"\n")
   print(nm,"notes")
file2.close()




