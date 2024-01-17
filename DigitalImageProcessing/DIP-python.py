import cv2
import numpy as np
from skimage import exposure
from skimage.metrics import peak_signal_noise_ratio
import matplotlib.pyplot as plt

# Load grayscale image of size 512x512
image = cv2.imread('your_image_path.jpg', cv2.IMREAD_GRAYSCALE)

# 1. Decrease spatial resolution
resized_images = []
while image.shape[0] > 1 and image.shape[1] > 1:
    image = cv2.resize(image, (image.shape[1] // 2, image.shape[0] // 2))
    resized_images.append(image.copy())

# 1. Decrease intensity level resolution
intensity_resolutions = []
for bit in range(8, 0, -1):
    intensity_resolutions.append((image >> bit) << bit)

# 1. Illustrate histogram and apply single threshold segmentation
hist, bins = np.histogram(image.flatten(), 256, [0, 256])
plt.plot(hist)
plt.title('Histogram')
plt.show()

threshold = 128  # Adjust threshold as needed
binary_image = (image > threshold).astype(np.uint8) * 255
plt.imshow(binary_image, cmap='gray')
plt.title('Threshold Segmentation')
plt.show()

# 2. Brightness enhancement
enhanced_image = exposure.rescale_intensity(image, in_range=(50, 200), out_range=(0, 255))

# 2. Power law transform and inverse logarithmic transform
gamma = 0.5
power_transformed = np.power(image / 255.0, gamma) * 255
log_transformed = exposure.adjust_log(image)

# 2. Find difference image
difference_image = image - (image & 0b111)  # Keep only first 5 bits (MSB)

# 3. Add salt & pepper noise
noisy_image = image.copy()
salt_pepper = np.random.rand(*image.shape)
noisy_image[salt_pepper < 0.01] = 0  # Salt noise
noisy_image[salt_pepper > 0.99] = 255  # Pepper noise

# 3. Apply average and median spatial filters
average_filtered = cv2.blur(noisy_image, (5, 5))
median_filtered = cv2.medianBlur(noisy_image, 5)

# 3. Apply different size of mask with average filter
psnr_results = []
for mask_size in [3, 5, 7]:
    filtered_image = cv2.blur(noisy_image, (mask_size, mask_size))
    psnr = peak_signal_noise_ratio(image, filtered_image)
    psnr_results.append((mask_size, psnr))

# 3. Apply harmonic and geometric mean filter
harmonic_mean_filtered = cv2.filter2D(noisy_image, -1, (1/9)*np.ones((3, 3)))
geometric_mean_filtered = np.power(np.prod(cv2.boxFilter(noisy_image, -1, (3, 3))), 1/9)

# 4. Add Gaussian noise
gaussian_noise = np.random.normal(0, 25, image.shape).astype(np.uint8)
noisy_image_gaussian = cv2.add(image, gaussian_noise)

# 4. Apply low pass filters
butterworth_filtered = cv2.bilateralFilter(noisy_image_gaussian, -1, 50, 50)
gaussian_filtered = cv2.GaussianBlur(noisy_image_gaussian, (5, 5), 0)

# 4. Observe ringing effect
ideal_low_pass_filtered = cv2.blur(noisy_image_gaussian, (30, 30))

# 4. Perform edge detection
laplacian = cv2.Laplacian(image, cv2.CV_64F)
sobel_x = cv2.Sobel(image, cv2.CV_64F, 1, 0, ksize=3)
sobel_y = cv2.Sobel(image, cv2.CV_64F, 0, 1, ksize=3)

# 5. Binary image and structuring element
binary_image = cv2.imread('binary_image_path.jpg', cv2.IMREAD_GRAYSCALE)
structuring_element = np.ones((3, 3), np.uint8)

# 5. Morphological operations
erosion = cv2.erode(binary_image, structuring_element, iterations=1)
dilation = cv2.dilate(binary_image, structuring_element, iterations=1)
opening = cv2.morphologyEx(binary_image, cv2.MORPH_OPEN, structuring_element)
closing = cv2.morphologyEx(binary_image, cv2.MORPH_CLOSE, structuring_element)

# 5. Boundary extraction
boundary = binary_image - erosion

# Display the results as needed
# (Note: Some results may need adjustments depending on your specific image and requirements)
