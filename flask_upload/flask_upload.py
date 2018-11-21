import pyrebase

config = {
    "apiKey": "AIzaSyAy1nWmjZ3-F5v_6ivgeG_bJaXhZrCtFM8",
    "authDomain": "test-a3da0.firebaseapp.com",
    "databaseURL": "https://test-a3da0.firebaseio.com",
    "projectId": "test-a3da0",
    "storageBucket": "test-a3da0.appspot.com",
    "messagingSenderId": "784675154021"
}

firebase = pyrebase.initialize_app(config)

db = firebase.database()
db.child("facetype").update({"facetype":"초기값","percent":"100"})

from flask import Flask, render_template, request
from werkzeug import secure_filename
from flask import send_file #태연이가 추가함
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
      f.save('/Users/changhyunho/flask/TeamProJect1/flask_upload/uploads/'+secure_filename(f.filename))

      arr = p.predict("./uploads/","./predict/")

      '''
      0 :'heart'
      1 :'oblong'
      2 :'oval'
      3 :'round'
      4 :'sqaure'
      '''
      lable = 10

      face = str(arr[0])

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
      
      db.child("facetype").update({"facetype":label, "percent":str(arr[1])})
      return '당신의 얼굴형은 '+label+"정확도는 "+str(arr[1])
#파일 다운로드 관리
@app.route('/output/out.csv') #this is a job for GET, not POST
def plot_csv():
    return send_file('output/out.csv',
                     mimetype='text/csv',
                     attachment_filename='out.csv',
                     as_attachment=True)
if __name__ == '__main__':
    #서버 실행
   app.run(port=8080, host='0.0.0.0')
