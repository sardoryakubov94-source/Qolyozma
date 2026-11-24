"""Veb versiyadan (../index.html) APK uchun assets/index.html yaratadi."""
import pathlib
here = pathlib.Path(__file__).parent
s = (here.parent / "index.html").read_text(encoding="utf-8")
cdn = '<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>'
assert cdn in s, "jsPDF CDN satri topilmadi"
s = s.replace(cdn, '<script src="jspdf.umd.min.js"></script>')
i = s.index("const dl={save:"); j = s.index("\n", i)
bridge = """const dl={save:async({filename,data})=>{const b=data instanceof Blob?data:new Blob([data]);
if(window.QAndroid){const u=new Uint8Array(await b.arrayBuffer());QAndroid.begin(filename);const CH=196608;
for(let i=0;i<u.length;i+=CH){let s='';const sub=u.subarray(i,i+CH);for(let k=0;k<sub.length;k+=8192)s+=String.fromCharCode.apply(null,sub.subarray(k,k+8192));QAndroid.chunk(btoa(s))}
QAndroid.finish();return{status:'saved'}}
const a=document.createElement('a');a.href=URL.createObjectURL(b);a.download=filename;document.body.appendChild(a);a.click();a.remove();setTimeout(()=>URL.revokeObjectURL(a.href),4000);return{status:'saved'}}};"""
s = s[:i] + bridge + s[j:]
(here / "assets" / "index.html").write_text(s, encoding="utf-8")
print("assets/index.html tayyor")
