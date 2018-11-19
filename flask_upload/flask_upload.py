from flask import Flask, render_template, request
from werkzeug import secure_filename
import predict as p
import numpy as np

app = Flask(__name__)

#업로드 HTML 렌더링
@app.route('/upload')
def render_file():
   return render_template('upload.html')

#파일 업로드 처리
@app.route('/fileUpload', methods = ['GET', 'POST'])
def upload_file():
   if request.method == 'POST':
      f = request.files['file']
      #저장할 경로 + 파일명
      f.save('./uploads/heart/'+secure_filename(f.filename))


      arr = p.predict("./uploads/","./predict/")

      '''
      0 :'heart'
      1 :'oblong'
      2 :'oval'
      3 :'round'
      4 :'sqaure'
      '''
      lable = 10

      face = arr[0]

      if (face == 0):
         lable = 'heart'
      elif face == 1:
         lable = 'oblong' 
      elif face == 2:
         lable = 'oval'
      elif face == 3:
         lable = 'round'
      elif face == 4:
         lable = 'square'
      
      return '당신의 얼굴형은 '+str(lable)

if __name__ == '__main__':
    #서버 실행
   app.run(debug = True)
