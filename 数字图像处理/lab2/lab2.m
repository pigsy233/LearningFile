clc;
clear;

%a
Sample = imread('sample.jpg');
%b
f1 = figure('Name','gaussian');
Sample = rgb2gray(Sample);
Sample_Gauss002 = imnoise(Sample,'gauss',0.002);
subplot(2,5,1)
imshow(Sample_Gauss002);
title('Gauss 0.002');
Sample_Gauss004 = imnoise(Sample,'gauss',0.004);
subplot(2,5,6);
imshow(Sample_Gauss004);
title('Gauss 0.004');
%c
ave33 = fspecial('average',3);
for i = 1:size(ave33,1)
    for j = 1:size(ave33,2)
        ave33(i,j) = -1;
    end
end
ave33(2,2) = 9;

%d-e
ave55 = fspecial('average',5);
for i = 1:size(ave55,1)
    for j = 1:size(ave55,2)
        ave55(i,j) = -1;
    end
end
ave55(3,3) = 25;

subplot(2,5,2);
imshow(filter2(ave33,Sample_Gauss002));
title('3x3 average');
subplot(2,5,3);
imshow(filter2(ave55,Sample_Gauss002));
title('5x5 average');
subplot(2,5,4);
imshow(medfilt2(Sample_Gauss002,[3,3]));
title('3x3 medium');
subplot(2,5,5);
imshow(medfilt2(Sample_Gauss002,[5,5]));
title('5x5 medium');

subplot(2,5,7);
imshow(filter2(ave33,Sample_Gauss004));
title('3x3 average');
subplot(2,5,8);
imshow(filter2(ave55,Sample_Gauss004));
title('5x5 average');
subplot(2,5,9);
imshow(medfilt2(Sample_Gauss004,[3,3]));
title('3x3 medium');
subplot(2,5,10);
imshow(medfilt2(Sample_Gauss004,[5,5]));
title('5x5 medium');

%f-g
f2 = figure('Name','salt & pepper');
Sample_sp002 = imnoise(Sample,'salt & pepper',0.002);
Sample_sp004 = imnoise(Sample,'salt & pepper',0.004);
subplot(2,5,1);
imshow(Sample_sp002);
title('salt & pepper 0.002');

subplot(2,5,1);
imshow(Sample_sp002);
title('salt & pepper 0.002');
subplot(2,5,6);
imshow(Sample_sp004);
title('salt & pepper 0.004');

subplot(2,5,2);
imshow(filter2(ave33,Sample_sp002));
title('3x3 average');
subplot(2,5,3);
imshow(filter2(ave55,Sample_sp002));
title('5x5 average');
subplot(2,5,4);
imshow(medfilt2(Sample_sp002,[3,3]));
title('3x3 medium');
subplot(2,5,5);
imshow(medfilt2(Sample_sp002,[5,5]));
title('5x5 medium');

subplot(2,5,7);
imshow(filter2(ave33,Sample_sp004));
title('3x3 average');
subplot(2,5,8);
imshow(filter2(ave55,Sample_sp004));
title('5x5 average');
subplot(2,5,9);
imshow(medfilt2(Sample_sp004,[3,3]));
title('3x3 medium');
subplot(2,5,10);
imshow(medfilt2(Sample_sp004,[5,5]));
title('5x5 medium');
