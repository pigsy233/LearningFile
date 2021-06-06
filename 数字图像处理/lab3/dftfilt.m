function g = dftfilt(f,H)
% 此函数可接受输入图像和一个滤波函数，可处理所有的
% 滤波细节并输出经滤波和剪切后的图像
% 将此.m文件保存在一个文件夹
% file->set path->add with subfolder
% 将你函数所在文件夹添加到搜索路径
% save就可以将其添加到你的函数库了
F=fft2(f,size(H,1),size(H,2));
g=real(ifft2(H.*F));
g=g(1:size(f,1),1:size(f,2));
